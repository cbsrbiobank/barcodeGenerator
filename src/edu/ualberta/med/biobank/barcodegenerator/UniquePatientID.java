package edu.ualberta.med.biobank.barcodegenerator;

import java.util.ArrayList;
import java.util.UUID;

public class UniquePatientID {

    private static String randString() {
        return UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "")
            .substring(0, 6)
            + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase().substring(0, 6);
    }

    private static ArrayList<String> randStringArray(int c) {
        ArrayList<String> l = new ArrayList<String>();
        for (int i = 0; i < c; i++) {
            l.add(randString());
        }
        return l;
    }

    //TODO add exceptions to patient ID generation
    
    // FIXME remove barcodes associated to a patient from the database
    public static boolean removePatient2DBarcodes(ArrayList<String> barcodes) {
        return true;
    }

    // FIXME generate and persit patient barcode IDs
    public static ArrayList<String> generatePatient2DBarcodes(String patientName)  {
        return randStringArray(32);
    }

}