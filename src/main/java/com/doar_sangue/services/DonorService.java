package com.doar_sangue.services;

import com.doar_sangue.entities.Donor;
import com.doar_sangue.repositories.DonorRepository;
import com.doar_sangue.services.rules.DonorRules;
import com.doar_sangue.services.rules.FemaleDonorRules;
import com.doar_sangue.services.rules.MaleDonorRules;
import com.doar_sangue.utils.DonorUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.doar_sangue.utils.DonorUtils.COMPATIBILIDADE;

@Service
public class DonorService {

    private final ObjectMapper objectMapper;
    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository, ObjectMapper objectMapper) {
        this.donorRepository = donorRepository;
        this.objectMapper = objectMapper;
    }


    public String uploadDonors(MultipartFile file) {

        try {
            List<Donor> donors = objectMapper.readValue(file.getInputStream(), new TypeReference<List<Donor>>() {});
            this.donorRepository.saveAll(donors
                    .stream()
                    .filter(this::canDonate)
                    .collect(Collectors.toList()));
            return "Arquivo processado e dados salvos com sucesso!";
        } catch (IOException e) {
            return "Erro ao processar o arquivo: " + e.getMessage();
        }
    }


    public Donor saveDonor(Donor donor) {

        if (!canDonate(donor))
            return null;

        return this.saveDonor(
                new Donor(
                        donor.getId(),
                        donor.getNome(),
                        donor.getRg(),
                        donor.getCpf(),
                        donor.getSexo(),
                        donor.getDataNasc(),
                        donor.getMae(),
                        donor.getPai(),
                        donor.getEmail(),
                        donor.getTelefoneFixo(),
                        donor.getCelular(),
                        donor.getPeso(),
                        donor.getAltura(),
                        donor.getTipoSanguineo(),
                        donor.getEndereco()
                )
        );
    }

    public List<Donor> getAllDonors() {
        return this.donorRepository.findAll();
    }

    public boolean canDonate(Donor donor) {
        DonorRules rules = donor.getSexo().equalsIgnoreCase("M")
                ? new MaleDonorRules()
                : new FemaleDonorRules();
        return rules.canDonate(donor);
    }

    public Map<String, Double> getAverageIMCByAgeGroup() {
        return this.donorRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        donor -> {
                            int idade = DonorUtils.calcAge(donor.getDataNasc());
                            return (idade / 10) * 10 + "-" + ((idade / 10) * 10 + 9);
                        },
                        Collectors.averagingDouble(donor -> donor.getPeso() / Math.pow(donor.getAltura(), 2))
                ));
    }

    public Map<String, Integer> donorsByState() {
        Map<String, Integer> contagemPorEstado = new HashMap<>();

        for (Donor don : this.donorRepository.findAll()) {
            String estado = don.getEndereco().getEstado();
            contagemPorEstado.put(estado, contagemPorEstado.getOrDefault(estado, 0) + 1);
        }

        return contagemPorEstado;
    }

    public Map<String, Double> calcPercentObesos() {
        int totalHomens = 0;
        int obesosHomens = 0;
        int totalMulheres = 0;
        int obesasMulheres = 0;

        for (Donor don : this.donorRepository.findAll()) {
            double altura = don.getAltura();
            double peso = don.getPeso();
            double imc = peso / (altura * altura); // Fórmula do IMC

            if ("Masculino".equalsIgnoreCase(don.getSexo())) {
                totalHomens++;
                if (imc > 30) {
                    obesosHomens++;
                }
            } else if ("Feminino".equalsIgnoreCase(don.getSexo())) {
                totalMulheres++;
                if (imc > 30) {
                    obesasMulheres++;
                }
            }
        }

        Map<String, Double> percentuais = new HashMap<>();
        percentuais.put("Percentual Obesos Homens", totalHomens > 0 ? (obesosHomens * 100.0) / totalHomens : 0.0);
        percentuais.put("Percentual Obesas Mulheres", totalMulheres > 0 ? (obesasMulheres * 100.0) / totalMulheres : 0.0);

        return percentuais;
    }

    public Map<String, Double> calcAVGAgeByTypeSanguineo() {

        Map<String, List<Donor>> agrupadosPorTipoSanguineo = this.donorRepository.findAll().stream()
                .collect(Collectors.groupingBy(Donor::getTipoSanguineo));


        Map<String, Double> mediaPorTipoSanguineo = new HashMap<>();

        for (Map.Entry<String, List<Donor>> entry : agrupadosPorTipoSanguineo.entrySet()) {
            String tipoSanguineo = entry.getKey();
            List<Donor> doadoresDoGrupo = entry.getValue();


            double mediaIdade = doadoresDoGrupo.stream()
                    .mapToInt(don -> DonorUtils.calcAge(don.getDataNasc()))
                    .average()
                    .orElse(0);

            mediaPorTipoSanguineo.put(tipoSanguineo, mediaIdade);
        }

        return mediaPorTipoSanguineo;
    }

    public Map<String, Integer> contDonorsByReceptors() {
        Map<String, Integer> contadorDoadores = new HashMap<>();

        for (String receptor : COMPATIBILIDADE.keySet()) {
            int totalDoadores = (int) this.donorRepository.findAll().stream()
                    .filter(c -> COMPATIBILIDADE.get(receptor).contains(c.getTipoSanguineo()))
                    .count();
            contadorDoadores.put(receptor, totalDoadores);
        }

        return contadorDoadores;
    }
}
