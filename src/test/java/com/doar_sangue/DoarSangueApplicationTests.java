package com.doar_sangue;

import com.doar_sangue.controllers.dto.AddressDTO;
import com.doar_sangue.controllers.dto.DonorDTO;
import com.doar_sangue.controllers.helpers.DonorHelper;
import com.doar_sangue.entities.Address;
import com.doar_sangue.entities.Donor;
import com.doar_sangue.repositories.DonorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ativa o Mockito no JUnit 5
class DoarSangueApplicationTests {

    @Mock
    private DonorHelper donorHelper;

    @Mock
    private DonorRepository donorRepository;

    @BeforeEach
    void setUp() {
        donorRepository = Mockito.mock(DonorRepository.class);
    }

    @Test
    void testGetAllDonors() {
        // Mockando os dados
        List<Donor> mockDonors = Arrays.asList(
                new Donor(1L, "João Silva", "1234567", "111.222.333-44", "M",
                        LocalDate.of(1990, 5, 15), "Maria Silva", "José Silva",
                        "joao@email.com", "11-1234-5678", "11-98765-4321",
                        75.5, 1.75, "O+",
                        new Address("Rua A", "123", "Centro", "São Paulo", "SP", 81000)),

                new Donor(2L, "Ana Souza", "7654321", "555.666.777-88", "F",
                        LocalDate.of(1985, 8, 22), "Clara Souza", "Miguel Souza",
                        "ana@email.com", "21-2345-6789", "21-98765-1234",
                        62.0, 1.65, "A-",
                        new Address("Avenida B", "456", "Bairro X", "Rio de Janeiro", "RJ", 22000)),

                new Donor(3L, "Carlos Oliveira", "9876543", "999.888.777-66", "M",
                        LocalDate.of(1992, 12, 10), "Lucia Oliveira", "Fernando Oliveira",
                        "carlos@email.com", "31-3456-7890", "31-91234-5678",
                        80.0, 1.80, "B+",
                        new Address("Rua C", "789", "Bairro Y", "Belo Horizonte", "MG", 31000))
        );

        // Mockando o comportamento do repositório
        when(donorRepository.findAll()).thenReturn(mockDonors);

        // Criando uma lista mockada de DonorDTOs
        List<DonorDTO> mockDonorDTOs = Arrays.asList(
                new DonorDTO(1L, "João Silva", "1234567", "111.222.333-44", "M",
                        LocalDate.of(1990, 5, 15), "Maria Silva", "José Silva",
                        "joao@email.com", "11-1234-5678", "11-98765-4321",
                        75.5, 1.75, "O+",
                        new AddressDTO("Rua A", "123", "Centro", "São Paulo", "SP", 81000)),

                new DonorDTO(2L, "Ana Souza", "7654321", "555.666.777-88", "F",
                        LocalDate.of(1985, 8, 22), "Clara Souza", "Miguel Souza",
                        "ana@email.com", "21-2345-6789", "21-98765-1234",
                        62.0, 1.65, "A-",
                        new AddressDTO("Avenida B", "456", "Bairro X", "Rio de Janeiro", "RJ", 22000)),

                new DonorDTO(3L, "Carlos Oliveira", "9876543", "999.888.777-66", "M",
                        LocalDate.of(1992, 12, 10), "Lucia Oliveira", "Fernando Oliveira",
                        "carlos@email.com", "31-3456-7890", "31-91234-5678",
                        80.0, 1.80, "B+",
                        new AddressDTO("Rua C", "789", "Bairro Y", "Belo Horizonte", "MG", 31000))
        );

        // Configurando o mock do donorHelper para retornar o mockDonorDTOs
        when(donorHelper.toModel(mockDonors)).thenReturn(mockDonorDTOs);

        // Chamando o método
        List<DonorDTO> donors = donorHelper.toModel(donorRepository.findAll());

        // Validando o resultado
        assertEquals(3, donors.size());
        assertEquals("João Silva", donors.get(0).getNome());
        assertEquals("Ana Souza", donors.get(1).getNome());
        assertEquals("Carlos Oliveira", donors.get(2).getNome());
    }
}
