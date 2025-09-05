package com.identifycourses.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class AllureReportOpener {

    private static final String ALLURE_BAT = "C:\\Users\\2421260\\allure-2.29.0\\bin\\allure.bat";

    private AllureReportOpener() {}

    public static void generateReportAndOpen() {
        try {
            Path resultsDir = Paths.get("target", "allure-results");
            if (!Files.exists(resultsDir)) {
                return;
            }
            try (var files = Files.list(resultsDir)) {
                if (!files.findAny().isPresent()) {
                    return; // no results to render
                }
            }

            runAllureCommand("generate", "--clean", "target/allure-results", "-o", "target/allure-report");

            File reportIndex = Paths.get("target", "allure-report", "index.html").toFile();
            if (reportIndex.exists()) {
                // Prefer opening via Allure to ensure correct static server
                runAllureCommand("open", "target/allure-report");
            }
        } catch (Exception ignored) {
            // Intentionally ignore failures to avoid breaking test runs
        }
    }

    private static void runAllureCommand(String... args) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("cmd.exe");
        command.add("/c");
        command.add(ALLURE_BAT);
        command.addAll(Arrays.asList(args));

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor(20, TimeUnit.SECONDS);
    }
} 