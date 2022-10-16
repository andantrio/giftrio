package com.fluffytrio.giftrio.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void addFileTest() throws Exception {
        String fileName = "dongggeul.jpeg";
        String filePath = "/file/dongggeul.jpeg";

        Resource fileResource = new ClassPathResource(filePath);
        assertNotNull(fileResource);

        MockMultipartFile firstFile = new MockMultipartFile(
                "file",
                fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());

        MockMvc mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).build();

        MvcResult mockResult = mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/v1/file")
                        .file(firstFile))
                .andExpect(status().is(200))
                .andReturn();

        String response = mockResult.getResponse().getContentAsString();
        assertThat(response.contains(fileName)).isTrue();
    }
}
