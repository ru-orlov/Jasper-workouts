package ru.orlov.jasperdemo.report;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReportOnStartupRunner implements CommandLineRunner {

    private final PeopleReportGenerator generator;

    public ReportOnStartupRunner(PeopleReportGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run(String... args) throws Exception {
        var pdf = generator.generatePdf();
        System.out.println("PDF generated: " + pdf.toAbsolutePath());
    }
}