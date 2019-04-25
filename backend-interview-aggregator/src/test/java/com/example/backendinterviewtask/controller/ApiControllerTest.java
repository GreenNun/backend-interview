package com.example.backendinterviewtask.controller;

import com.example.backendinterviewtask.reports.BrandMetricsReportGenerator;
import com.example.backendinterviewtask.reports.MetricsReportGenerator;
import com.example.backendinterviewtask.reports.TimedBrandMetricsReportGenerator;
import com.example.backendinterviewtask.reports.TimedMetricsReportGenerator;
import com.example.backendinterviewtask.reports.impl.BrandMetricsReportGeneratorImpl;
import com.example.backendinterviewtask.reports.impl.MetricsReportGeneratorImpl;
import com.example.backendinterviewtask.reports.impl.TimedBrandMetricsReportGeneratorImpl;
import com.example.backendinterviewtask.reports.impl.TimedMetricsReportGeneratorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by GreenNun on 2019-04-22.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ApiControllerTest {
    @MockBean
    private MetricsReportGeneratorImpl metricsReportGenerator;
    @MockBean
    private TimedMetricsReportGeneratorImpl timedMetricsReportGenerator;
    @MockBean
    private BrandMetricsReportGeneratorImpl brandMetricsReportGenerator;
    @MockBean
    private TimedBrandMetricsReportGeneratorImpl timedBrandMetricsReportGenerator;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void metricsReport() throws Exception {
        mockMvc.perform(get("/v1/metricsReport").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(metricsReportGenerator, times(1)).generate();
    }

    @Test
    public void timedMetricsReports() throws Exception {
        mockMvc.perform(get("/v1/timedMetricsReports").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(timedMetricsReportGenerator, times(1)).generate();
    }

    @Test
    public void brandMetricsReports() throws Exception {
        mockMvc.perform(get("/v1/brandMetricsReports").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(brandMetricsReportGenerator, times(1)).generate();
    }

    @Test
    public void timedBrandMetricsReport() throws Exception {
        mockMvc.perform(get("/v1/timedBrandMetricsReport").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(timedBrandMetricsReportGenerator, times(1)).generate();
    }
}