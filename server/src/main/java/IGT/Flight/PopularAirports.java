package IGT.Flight;

import IGT.Hibernate;

public class PopularAirports {
    public static String[] airports = {"Hartsfield–Jackson Atlanta International Airport ATL/KATL",
            "Beijing Capital International Airport PEK/ZBAA",
            "Dubai International Airport DXB/OMDB",
            "Tokyo Haneda Airport HND/RJTT",
            "Los Angeles International Airport LAX/KLAX",
            "O'Hare International Airport ORD/KORD",
            "London Heathrow Airport LHR/EGLL",
            "Hong Kong International Airport HKG/VHHH",
            "Shanghai Pudong International Airport PVG/ZSPD",
            "Paris-Charles de Gaulle Airport CDG/LFPG",
            "Amsterdam Airport AMS/EHAM",
            "Dallas/Fort Worth International Airport DFW/KDFW",
            "Guangzhou Baiyun International Airport CAN/ZGGG",
            "Frankfurt Airport FRA/EDDF",
            "Istanbul Atatürk Airport IST/LTBA",
            "Indira Gandhi International Airport DEL/VIDP",
            "Soekarno-Hatta International Airport CGK/WIII",
            "Singapore Changi Airport SIN/WSSS",
            "Seoul Incheon International Airport ICN/RKSI",
            "Denver International Airport DEN/KDEN",
            "Suvarnabhumi Airport BKK/VTBS",
            "John F. Kennedy International Airport JFK/KJFK",
            "Kuala Lumpur International Airport KUL/WMKK",
            "San Francisco International Airport SFO/KSFO",
            "Madrid Barajas Airport MAD/LEMD",
            "Chengdu Shuangliu International Airport CTU/ZUUU",
            "McCarran International Airport LAS/KLAS",
            "Barcelona–El Prat Airport BCN/LEBL",
            "Chhatrapati Shivaji International BOM/VABB",
            "Toronto Pearson International Airport YYZ/CYYZ",
            "Seattle-Tacoma International Airport SEA/KSEA",
            "Charlotte Douglas International Airport CLT/KCLT",
            "London Gatwick Airport LGW/EGKK",
            "Shenzhen Bao'an International Airport SZX/ZGSZ",
            "Taiwan Taoyuan International Airport TPE/RCTP"};

    public static void generate() throws Exception {
        for (String airport : airports) {
            String[] all = airport.split(" ");
            String shortName = all[all.length - 1];
            String name = airport.split(shortName)[0].trim();
            System.out.println("create Airport " + name + " (" + shortName + ")");
            Hibernate.getInstance().save(new Airport(name, shortName));
        }
    }
}
