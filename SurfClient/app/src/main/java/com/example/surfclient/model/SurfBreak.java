package com.example.surfclient.model;

public enum SurfBreak {
    BEACHBREAK, REEFBREAK, POINTBREAK, OUTERBANKS;
    // Méthode pour obtenir une version lisible
    public String getDisplayName() {
        switch (this) {
            case BEACHBREAK:
                return "Beach Break";
            case REEFBREAK:
                return "Reef Break";
            case POINTBREAK:
                return "Point Break";
            case OUTERBANKS:
                return "Outer Banks";
            default:
                return this.name(); // Par défaut, retourne le nom brut
        }
    }
}

