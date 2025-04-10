package co.ufps.expo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableTask
@Slf4j
public class SystemStatsTask implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("Ejecutando tarea de estadísticas del sistema: {}",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        log.info("Sistema Operativo: {}", osBean.getName());
        log.info("Arquitectura: {}", osBean.getArch());
        log.info("Procesadores disponibles: {}", osBean.getAvailableProcessors());
        log.info("Carga del sistema: {}", osBean.getSystemLoadAverage());
        log.info("Memoria heap usada: {} MB", memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024));
        log.info("Memoria heap máxima: {} MB", memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024));

        log.info("Tarea de estadísticas completada");
    }
}