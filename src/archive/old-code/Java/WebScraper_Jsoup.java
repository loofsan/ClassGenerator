package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WebScraper_Jsoup {
    public void getData() {

        /*
        //--- Fall Stuff ---
        final String fallScheduleUrl = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Accounting&department_desc%5B%5D=Administration+of+J" +
                "ustice&department_desc%5B%5D=American+Sign+Language&d" +
                "epartment_desc%5B%5D=Anthropology&department_desc%5B%" +
                "5D=Aquatics&department_desc%5B%5D=Architecture&depart" +
                "ment_desc%5B%5D=Art&department_desc%5B%5D=Astronomy&d" +
                "epartment_desc%5B%5D=Automotive+Technology&department" +
                "_desc%5B%5D=Barbering&department_desc%5B%5D=Biology&d" +
                "epartment_desc%5B%5D=Biotechnology&term_code=202308&c" +
                "ollege_code%5B%5D=3&college_code%5B%5D=4&college_code" +
                "%5B%5D=2";
        final String fallClassesOutputPath = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall.json";
        final String fallScheduleUrl2 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Business+Computer+Sys%2FMgmt&department_desc%5B%5D=Car" +
                "eer+and+Personal+Develop&department_desc%5B%5D=Chemist" +
                "ry&department_desc%5B%5D=Chinese&department_desc%5B%5D" +
                "=Communication+Studies&department_desc%5B%5D=Comp.+Bus" +
                ".+Office+Technology&department_desc%5B%5D=Computer+Scie" +
                "nce&department_desc%5B%5D=Cooperative+Education&departm" +
                "ent_desc%5B%5D=Cosmetology&department_desc%5B%5D=Couns" +
                "eling&term_code=202308&college_code%5B%5D=3&college_co" +
                "de%5B%5D=4&college_code%5B%5D=2";
        final String fallClassesOutputPath2 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall2.json";
        final String fallScheduleUrl3 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Dance&department_desc%5B%5D=Dental+Assisting&departme" +
                "nt_desc%5B%5D=Digital+Art+and+Animation&department_des" +
                "c%5B%5D=Digital+Media&department_desc%5B%5D=Digital+M" +
                "edia+%26+Design&department_desc%5B%5D=Drafting+Techno" +
                "logy&department_desc%5B%5D=Drama+-+Theatre+Arts&depart" +
                "ment_desc%5B%5D=Early+Childhood+Education&department_d" +
                "esc%5B%5D=Economics&department_desc%5B%5D=Education&de" +
                "partment_desc%5B%5D=Education+and+Human+Development&te" +
                "rm_code=202308&college_code%5B%5D=3&college_code%5B%5D" +
                "=4&college_code%5B%5D=2";
        final String fallClassesOutputPath3 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall3.json";
        final String fallScheduleUrl4 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Educational+Access&department_desc%5B%5D=Electronics" +
                "&department_desc%5B%5D=Emergency+Medical+Care&departm" +
                "ent_desc%5B%5D=Energy+Systems+Technology+Mgmt&departm" +
                "ent_desc%5B%5D=Engineering&department_desc%5B%5D=Engl" +
                "ish&department_desc%5B%5D=English+Second+Language&dep" +
                "artment_desc%5B%5D=Environmental+Science&department_d" +
                "esc%5B%5D=Esthetics&department_desc%5B%5D=Ethnic+Stud" +
                "ies&department_desc%5B%5D=Fashion+Design&department_d" +
                "esc%5B%5D=Filipino&term_code=202308&college_code%5B%5" +
                "D=3&college_code%5B%5D=4&college_code%5B%5D=2";
        final String fallClassesOutputPath4 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall4.json";
        final String fallScheduleUrl5 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Film&department_desc%5B%5D=Fire+Science+Technology&dep" +
                "artment_desc%5B%5D=Funeral+Service+Education&departmen" +
                "t_desc%5B%5D=Geography&department_desc%5B%5D=Geology&d" +
                "epartment_desc%5B%5D=Global+Studies&department_desc%5B" +
                "%5D=Health+Science&department_desc%5B%5D=History&depar" +
                "tment_desc%5B%5D=Hospitality%2FTourism+Management&depa" +
                "rtment_desc%5B%5D=Human+Services&term_code=202308&coll" +
                "ege_code%5B%5D=3&college_code%5B%5D=4&college_code%5B%" +
                "5D=2";
        final String fallClassesOutputPath5 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall5.json";
        final String fallScheduleUrl6 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Interdisciplinary+Studies&department_desc%5B%5D=Interi" +
                "or+Design&department_desc%5B%5D=Journalism&department_" +
                "desc%5B%5D=Kinesiology&department_desc%5B%5D=Learning+" +
                "Center&department_desc%5B%5D=Learning+Skills&departmen" +
                "t_desc%5B%5D=Library+Science&department_desc%5B%5D=Lin" +
                "guistics&department_desc%5B%5D=Literature&department_de" +
                "sc%5B%5D=Management&term_code=202308&college_code%5B%5D" +
                "=3&college_code%5B%5D=4&college_code%5B%5D=2";
        final String fallClassesOutputPath6 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall6.json";
        final String fallScheduleUrl7 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Mathematics&department_desc%5B%5D=Medical+Assisting&" +
                "department_desc%5B%5D=Multimedia&department_desc%5B%5" +
                "D=Music&department_desc%5B%5D=Network+Engineering+Tec" +
                "hnology&department_desc%5B%5D=Nursing&department_desc" +
                "%5B%5D=Oceanography&department_desc%5B%5D=Paleontolog" +
                "y&department_desc%5B%5D=Paralegal+Studies&department_" +
                "desc%5B%5D=Philosophy&term_code=202308&college_code%" +
                "5B%5D=3&college_code%5B%5D=4&college_code%5B%5D=2";
        final String fallClassesOutputPath7 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall7.json";
        final String fallScheduleUrl8 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Photonics+and+Laser+Technology&department_desc%5B%5D=P" +
                "hysical+Education&department_desc%5B%5D=Physical+Scien" +
                "ce&department_desc%5B%5D=Physics&department_desc%5B%5D" +
                "=Political+Science&department_desc%5B%5D=Psychology&de" +
                "partment_desc%5B%5D=Public+%26+Nonprofit+Administra&te" +
                "rm_code=202308&college_code%5B%5D=3&college_code%5B%5D" +
                "=4&college_code%5B%5D=2";
        final String fallClassesOutputPath8 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall8.json";
        final String fallScheduleUrl9 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Radiologic+Technology&department_desc%5B%5D=Real+Estate" +
                "&department_desc%5B%5D=Respiratory+Therapy&department_d" +
                "esc%5B%5D=Social+Justice+Studies&department_desc%5B%5D=" +
                "Social+Science&department_desc%5B%5D=Sociology&departme" +
                "nt_desc%5B%5D=Spanish&department_desc%5B%5D=Surgical+Te" +
                "chnology&department_desc%5B%5D=Wellness&term_code=202308" +
                "&college_code%5B%5D=3&college_code%5B%5D=4&college_code" +
                "%5B%5D=2";
        final String fallClassesOutputPath9 = "src/main/resources/" +
                "WebScheduleClassesFall/" +
                "WebScheduleClassesFall9.json";

        getWebScheduleData(fallScheduleUrl9, fallClassesOutputPath9);
        getWebScheduleData(fallScheduleUrl8, fallClassesOutputPath8);
        getWebScheduleData(fallScheduleUrl7, fallClassesOutputPath7);
        getWebScheduleData(fallScheduleUrl6, fallClassesOutputPath6);
        getWebScheduleData(fallScheduleUrl5, fallClassesOutputPath5);
        getWebScheduleData(fallScheduleUrl4, fallClassesOutputPath4);
        getWebScheduleData(fallScheduleUrl3, fallClassesOutputPath3);
        getWebScheduleData(fallScheduleUrl2, fallClassesOutputPath2);
        getWebScheduleData(fallScheduleUrl, fallClassesOutputPath);

        // --- Spring Stuff ---
        final String springScheduleUrl = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Accounting&department_desc%5B%5D=Administration+of+J" +
                "ustice&department_desc%5B%5D=American+Sign+Language&d" +
                "epartment_desc%5B%5D=Anthropology&department_desc%5B%" +
                "5D=Aquatics&department_desc%5B%5D=Architecture&depart" +
                "ment_desc%5B%5D=Art&department_desc%5B%5D=Astronomy&d" +
                "epartment_desc%5B%5D=Automotive+Technology&department" +
                "_desc%5B%5D=Barbering&department_desc%5B%5D=Biology&d" +
                "epartment_desc%5B%5D=Biotechnology&term_code=202403&c" +
                "ollege_code%5B%5D=3&college_code%5B%5D=4&college_code" +
                "%5B%5D=2";
        final String springClassesOutputPath = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring.json";
        final String springScheduleUrl2 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Building+Inspection&department_desc%5B%5D=Business&d" +
                "epartment_desc%5B%5D=Business+Computer+Sys%2FMgmt&dep" +
                "artment_desc%5B%5D=Career+and+Personal+Develop&depart" +
                "ment_desc%5B%5D=Chemistry&department_desc%5B%5D=Chine" +
                "se&department_desc%5B%5D=Communication+Studies&depart" +
                "ment_desc%5B%5D=Comp.+Bus.+Office+Technology&departme" +
                "nt_desc%5B%5D=Computer+Science&department_desc%5B%5D=" +
                "Cooperative+Education&term_code=202403&college_code%5" +
                "B%5D=3&college_code%5B%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath2 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring2.json";
        final String springScheduleUrl3 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Cosmetology&department_desc%5B%5D=Counseling&departm" +
                "ent_desc%5B%5D=Dance&department_desc%5B%5D=Dental+Ass" +
                "isting&department_desc%5B%5D=Digital+Art+and+Animatio" +
                "n&department_desc%5B%5D=Digital+Media&department_desc" +
                "%5B%5D=Digital+Media+%26+Design&department_desc%5B%5D" +
                "=Drafting+Technology&department_desc%5B%5D=Drama+-+Th" +
                "eatre+Arts&term_code=202403&college_code%5B%5D=3&coll" +
                "ege_code%5B%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath3 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring3.json";
        final String springScheduleUrl4 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D" +
                "=Early+Childhood+Education&department_desc%5B%5D=Econ" +
                "omics&department_desc%5B%5D=Education&department_desc" +
                "%5B%5D=Education+and+Human+Development&department_des" +
                "c%5B%5D=Educational+Access&department_desc%5B%5D=Elec" +
                "tronics&department_desc%5B%5D=Emergency+Medical+Care&" +
                "department_desc%5B%5D=Energy+Systems+Technology+Mgmt&" +
                "department_desc%5B%5D=Engineering&department_desc%5B%" +
                "5D=English&term_code=202403&college_code%5B%5D=3&coll" +
                "ege_code%5B%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath4 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring4.json";
        final String springScheduleUrl5 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "English+Second+Language&department_desc%5B%5D=Environm" +
                "ental+Science&department_desc%5B%5D=Esthetics&departme" +
                "nt_desc%5B%5D=Ethnic+Studies&department_desc%5B%5D=Fas" +
                "hion+Design&department_desc%5B%5D=Filipino&department_" +
                "desc%5B%5D=Film&department_desc%5B%5D=Fire+Science+Tec" +
                "hnology&department_desc%5B%5D=Funeral+Service+Education" +
                "&term_code=202403&college_code%5B%5D=3&college_code%5B%" +
                "5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath5 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring5.json";
        final String springScheduleUrl6 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Geography&department_desc%5B%5D=Geology&department_de" +
                "sc%5B%5D=Global+Studies&department_desc%5B%5D=Health+" +
                "Science&department_desc%5B%5D=History&department_desc%" +
                "5B%5D=Hospitality%2FTourism+Management&department_de" +
                "sc%5B%5D=Human+Services&department_desc%5B%5D=Interdi" +
                "sciplinary+Studies&department_desc%5B%5D=Interior+Des" +
                "ign&department_desc%5B%5D=Journalism&term_code=202403" +
                "&college_code%5B%5D=3&college_code%5B%5D=4&college_cod" +
                "e%5B%5D=2";
        final String springClassesOutputPath6 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring6.json";
        final String springScheduleUrl7 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Kinesiology&department_desc%5B%5D=Learning+Center&dep" +
                "artment_desc%5B%5D=Learning+Skills&department_desc%5B" +
                "%5D=Library+Science&department_desc%5B%5D=Linguistics&" +
                "department_desc%5B%5D=Literature&department_desc%5B%5D" +
                "=Management&department_desc%5B%5D=Mathematics&departmen" +
                "t_desc%5B%5D=Medical+Assisting&department_desc%5B%5D=Mu" +
                "ltimedia&department_desc%5B%5D=Music&term_code=202403&" +
                "college_code%5B%5D=3&college_code%5B%5D=4&college_cod" +
                "e%5B%5D=2";
        final String springClassesOutputPath7 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring7.json";
        final String springScheduleUrl8 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Network+Engineering+Technology&department_desc%5B%5D=N" +
                "ursing&department_desc%5B%5D=Oceanography&department_d" +
                "esc%5B%5D=Paleontology&department_desc%5B%5D=Paralegal+" +
                "Studies&department_desc%5B%5D=Philosophy&department_desc" +
                "%5B%5D=Photonics+and+Laser+Technology&department_desc%5B" +
                "%5D=Physical+Education&department_desc%5B%5D=Physical+S" +
                "cience&term_code=202403&college_code%5B%5D=3&college_cod" +
                "e%5B%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath8 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring8.json";
        final String springScheduleUrl9 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Physics&department_desc%5B%5D=Political+Science&depart" +
                "ment_desc%5B%5D=Psychology&department_desc%5B%5D=Publi" +
                "c+%26+Nonprofit+Administra&department_desc%5B%5D=Radiol" +
                "ogic+Technology&department_desc%5B%5D=Real+Estate&depar" +
                "tment_desc%5B%5D=Respiratory+Therapy&department_desc%5B" +
                "%5D=Social+Justice+Studies&department_desc%5B%5D=Social" +
                "+Science&term_code=202403&college_code%5B%5D=3&college_" +
                "code%5B%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath9 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring9.json";
        final String springScheduleUrl10 = "https://webschedule.smccd.edu/" +
                "list_classes_default_search.php?department_desc%5B%5D=" +
                "Sociology&department_desc%5B%5D=Spanish&department_desc" +
                "%5B%5D=Surgical+Technology&department_desc%5B%5D=Wellne" +
                "ss&term_code=202403&college_code%5B%5D=3&college_code%5B" +
                "%5D=4&college_code%5B%5D=2";
        final String springClassesOutputPath10 = "src/main/resources/" +
                "WebScheduleSpring/WebScheduleClassesSpring10.json";

        getWebScheduleData(springScheduleUrl10, springClassesOutputPath10);
        getWebScheduleData(springScheduleUrl9, springClassesOutputPath9);
        getWebScheduleData(springScheduleUrl8, springClassesOutputPath8);
        getWebScheduleData(springScheduleUrl7, springClassesOutputPath7);
        getWebScheduleData(springScheduleUrl6, springClassesOutputPath6);
        getWebScheduleData(springScheduleUrl5, springClassesOutputPath5);
        getWebScheduleData(springScheduleUrl4, springClassesOutputPath4);
        getWebScheduleData(springScheduleUrl3, springClassesOutputPath3);
        getWebScheduleData(springScheduleUrl2, springClassesOutputPath2);
        getWebScheduleData(springScheduleUrl, springClassesOutputPath);
        */
    }

    private void getWebScheduleData(String url, String outputPath) {

        /*
        So, I learned that Intellij suggests using AtomicInteger when there are
        multiple threads concurrently accessing and modifying the same variable

        Atomic Integer is better because:
        - creates a single, invisible step that
          race conditions that can occur when multiple
          threads are accessing the same variable at the same time.
        - Synchronizes internally
        - Thread Safety
        - Consistent and Predictable Behavior

        However,
        - In the case that overhead is low,
          the use of atomic integers might be unnecessary

        -----------------------------------------------------------------------

        I was also going to use threads and a volatile variable, but I
        chose to use ScheduledExecutorService because I had a Thread.sleep()
        function in a loop without an actual condition, and it can be considered
        busy waiting. Thus, I went for a more controlled approach,
        Scheduled Executor Service
         */
        final int dotsPerLine = 3;
        AtomicInteger dotsCount = new AtomicInteger();
        System.out.println("Writing data to " + outputPath + ": ");

        ScheduledExecutorService executorService =
                Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            System.out.print("。");
            int count = dotsCount.incrementAndGet();
            if (count >= dotsPerLine) {
                System.out.println("\n");
                dotsCount.set(0);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        final Document document;
        int timeInMilliSeconds = 600000;

        try {
            Connection connect = Jsoup.connect(url);
            connect.timeout(timeInMilliSeconds);
            document = connect.get();

            //Elements tables = document.select
            //("table.table courses table-hover");

            Elements divs = document.select("div.list");

            //JSONObject jsonObject = new JSONObject();
            Map<String, JSONArray> subjectMap = new HashMap<>();

            for(int divIndex = 0; divIndex < divs.size(); divIndex++) {
                Element div = divs.get(divIndex);
                Element table = div.select
                        ("table.table.courses.table-hover").get(0);

                //Capture the list header
                Element headerElement = div.selectFirst("h2.list-header");
                String listHeader = (headerElement != null) ?
                        headerElement.text() : "Unknown Header";

                System.out.println("Scraping data from List Header '" +
                        listHeader + "' (Table " +
                        (divIndex + 1));

                // Use the subject name as the key in the map
                JSONArray classArray = subjectMap.computeIfAbsent(listHeader,
                        k -> new JSONArray());

                Elements tableRows = table.select("tr");

                for (int i = 1; i < tableRows.size(); i++)
                {
                    Element tableRow = tableRows.get(i);

                    Element status = tableRow.selectFirst
                            ("td.hidden-xs:nth-child(2) > div > p");

                    Element CRN = tableRow.selectFirst
                            ("td.hidden-xs:nth-child(3)");

                    Element courseTitle = tableRow.selectFirst
                            ("a.course-title");

                    Element professor = tableRow.selectFirst
                            ("td.hidden-xs:nth-child(7)");

                    Element classDays = tableRow.selectFirst
                            ("td.hidden-xs:nth-child(6)");

                    Element units = tableRow.selectFirst
                            ("td.hidden-xs:nth-child(5)");

                    String statusText = (status != null) ?
                            status.text() : "";

                    String CRNText = (CRN != null) ?
                            CRN.text() : "";

                    String courseTitleText = (courseTitle != null) ?
                            courseTitle.text().split("\n")[0] : "";

                    String professorText = (professor != null) ?
                            professor.text() : "";

                    String classDaysText = (classDays != null) ?
                            classDays.text() : "";

                    String unitsText = (units != null) ?
                            units.text() : "";

                    if (CRNText.isEmpty())
                    {
                        CRNText = "0";
                    }
                    if (unitsText.isEmpty())
                    {
                        unitsText = "0";
                    }
                    if (unitsText.contains("OR") ||
                            unitsText.contains("TO"))
                    {
                        unitsText = parseNumber(unitsText);
                    }

                    JSONObject classObject = new JSONObject();
                    classObject.put("Class", i);
                    classObject.put("Status", statusText);
                    classObject.put("Course Title", courseTitleText);
                    classObject.put("CRN", Integer.parseInt(CRNText));
                    classObject.put("Professor", professorText);
                    classObject.put("Class Days", classDaysText);
                    classObject.put("Units", Double.parseDouble(unitsText));


                    classArray.put(classObject);
                }

                subjectMap.put(listHeader, classArray);
            }

            executorService.shutdown();

            JSONObject JsonObject = new JSONObject(subjectMap);


            try (Writer writer = new FileWriter(outputPath)) {
                writer.write(JsonObject.toString(4));
                System.out.println("\nData written at: " + outputPath);
            }

        } catch (IOException e) {
            // Log the exception
            throw new RuntimeException("Failed to scrape data", e);
        }
    }

    private String parseNumber(String input) {
        // Split the input string using the "or" as a delimiter
        String[] parts = input.split("\\s+or\\s+");

        // Take the first part, which should contain the number you want
        String firstPart = parts[0];

        // Use regular expression to extract the first number

        return firstPart.replaceAll("[^0-9]", "");
    }
}
