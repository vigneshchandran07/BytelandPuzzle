package com.vignesh.learning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BytelandPuzzle {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting analysis");
        int noOfSteps = 0;
        String path = "/home/vignesh/IdeaProjects/puzzle/src/main/resources";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path + "/output.txt")));

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path + "/input.txt")));

        int t = Integer.parseInt(bufferedReader.readLine());
        System.out.println("No: Of Test Cases : " + t);
        for (int i = 0; i < t; i++) {
            System.out.println("Test Case no " + i);
            String line1 = bufferedReader.readLine();
            int noOfPlots = Integer.parseInt(line1.split(" ")[0]);
            int priceForEachPlotByCompany = Integer.parseInt(line1.split(" ")[1]);

            String line2 = bufferedReader.readLine();
            String[] line2Array = line2.split("\\s+");
            List<String> list = Arrays.asList(line2Array);
            List<Integer> plotPrices = list.stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());


            int finalProfit = 0;

            for (int loopCounter = 0; loopCounter < noOfPlots; loopCounter++) {
                noOfSteps++;
                int small = plotPrices.stream().skip(loopCounter).mapToInt(Integer::intValue).sum();
                if ((noOfPlots - (loopCounter)) * priceForEachPlotByCompany > small) {
                    int profit = ((noOfPlots - loopCounter) * priceForEachPlotByCompany) - small;
                    if (profit > finalProfit) {
                        finalProfit = profit;
                    }
                }
                List<Integer> subset = plotPrices.stream().skip(loopCounter).collect(Collectors.toList());
                Collections.reverse(subset);
                for (int loopCounter2 = 1; loopCounter2 < subset.size(); loopCounter2++) {
                    noOfSteps++;
                    int small2 = subset.stream().skip(loopCounter2).mapToInt(Integer::intValue).sum();
                    if ((noOfPlots - loopCounter - (loopCounter2)) * priceForEachPlotByCompany > small2) {
                        int profit = ((noOfPlots - loopCounter - loopCounter2) * priceForEachPlotByCompany) - small2;
                        if (profit > finalProfit) {
                            finalProfit = profit;
                        }
                    }
                }

            }
            bufferedWriter.write(Integer.toString(finalProfit));
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
        System.out.println(noOfSteps);
    }
}
