package com.felipe.centraldesk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.centraldesk.api.dto.CriarChamadoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.context.support.WithMockUser.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser
public class ChamadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarChamadoComSucesso() throws Exception {
        CriarChamadoRequest request = new CriarChamadoRequest();
        request.setTitulo("Titulo teste");
        request.setDescricao("Descrição teste");
        request.setUsuarioId(1L);
        request.setGrupoId(1L);

        mockMvc.perform(post("/chamados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ABERTO"));
    }

    @Test
    void deveRetornar400QuandoTituloInvalido() throws Exception {
        CriarChamadoRequest request = new CriarChamadoRequest();
        request.setTitulo("");
        request.setDescricao("curta");
        request.setUsuarioId(null);
        request.setGrupoId(null);

        mockMvc.perform(post("/chamados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar404QuandoChamadoNaoExiste() throws Exception{
        mockMvc.perform(get("/chamados/9999"))
                .andExpect(status().isNotFound());
    }
}
