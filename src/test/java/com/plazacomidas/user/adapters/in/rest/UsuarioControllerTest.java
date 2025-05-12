package com.plazacomidas.user.adapters.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.port.in.CrearUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrearUsuarioUseCase crearUsuarioUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearPropietario_DeberiaRetornar200YUsuarioCreado() throws Exception {
        CrearUsuarioCommand command = CrearUsuarioCommand.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .documentoDeIdentidad("123456789")
                .celular("+573001112233")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .correo("juan@mail.com")
                .clave("claveEncriptada")
                .build();

        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNombre(command.getNombre());
        usuarioMock.setApellido(command.getApellido());
        usuarioMock.setDocumentoDeIdentidad(command.getDocumentoDeIdentidad());
        usuarioMock.setCorreo(command.getCorreo());
        usuarioMock.setCelular(command.getCelular());
        usuarioMock.setFechaNacimiento(command.getFechaNacimiento());
        usuarioMock.setClave(command.getClave());

        Mockito.when(crearUsuarioUseCase.crearPropietario(any())).thenReturn(usuarioMock);

        mockMvc.perform(post("/usuarios/propietario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.correo").value("juan@mail.com"));
    }
}
