package ru.orlov.jasperdemo.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.json.data.JsonDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

@Component
public class PeopleReportGenerator {

    public Path generatePdf() throws Exception {
        // 1) Берём реальный файл из classpath как Path
        Path jrxmlPath = new ClassPathResource("reports/people.jrxml").getFile().toPath();

        // 2) Компилируем в .jasper рядом (можно и в temp/target)
        Path compiledPath = jrxmlPath.getParent().resolve("people.jasper");
        JasperCompileManager.compileReportToFile(jrxmlPath.toString(), compiledPath.toString());

        // 3) Загружаем .jasper
        JasperReport report = (JasperReport) JRLoader.loadObject(compiledPath.toFile());

        // 4) Данные + fill + export
        try (InputStream json = new ClassPathResource("data/people.json").getInputStream()) {
            JsonDataSource dataSource = new JsonDataSource(json, "people");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

            Path outDir = Path.of("output");
            Files.createDirectories(outDir);

            Path pdf = outDir.resolve("people.pdf");
            JasperExportManager.exportReportToPdfFile(print, pdf.toAbsolutePath().toString());
            return pdf;
        }
    }
}