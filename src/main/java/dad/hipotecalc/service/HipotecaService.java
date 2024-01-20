package dad.hipotecalc.service;

import dad.hipotecalc.model.Hipoteca;

public class HipotecaService {
    private Hipoteca hipoteca;

    public HipotecaService() {
        hipoteca = new Hipoteca();
    }

    // Métodos para manipular y acceder a la hipoteca
    public Hipoteca getHipoteca() {
        return hipoteca;
    }
    
    // Otros métodos relevantes...
}

