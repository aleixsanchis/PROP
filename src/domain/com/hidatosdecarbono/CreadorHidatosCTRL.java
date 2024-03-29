package com.hidatosdecarbono;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CreadorHidatosCTRL {

    private Hidato hidatoCreado;
    private PersistenciaCTRL persistenciaCTRL;
    private JugarHidatosCTRL jugarHidatosCTRL;
    private LogInCTRL logInCTRL;


    public CreadorHidatosCTRL(PersistenciaCTRL persistenciaCTRL, JugarHidatosCTRL jugarHidatosCTRL, LogInCTRL logInCTRL){
        this.persistenciaCTRL = persistenciaCTRL;
            this.jugarHidatosCTRL = jugarHidatosCTRL;
        this.logInCTRL = logInCTRL;
    }

    public int getnFilasHidato(){
        return hidatoCreado.getNumFilas();
    }

    public int getnColsHidato(){
        return hidatoCreado.getNumColumnas();
    }
    /**
     * Devuelve el hidato creado.
     * @return Un Hidato
     */
    public Hidato getHidatoCreado(){
        return hidatoCreado;
    }

    /**
     *Constructora para un Hidato propuesto por el usuario.
     * @param tipoHidato Un enum TipoHidato que contiene el tipo del Hidato a crear
     * @param numFilas Un integer con el numero de filas del hidato a crear
     * @param numColumnas Un integer con el numero de columnas del hidato a crear
     * @param tipoAdyacencia Un enum TipoAdyacencia que contiene el tipo de la adyacencia del hidato a crear
     * @return Un boolean que indica si el hidato propuesto tiene o no solución
     */
    public void creaHidatoPropuesto(TipoHidato tipoHidato, int numFilas, int numColumnas, TipoAdyacencia tipoAdyacencia) throws IllegalArgumentException{
        if(tipoHidato.equals(TipoHidato.CUADRADO)) {
            hidatoCreado = new HidatoCuadrado(numFilas, numColumnas, tipoAdyacencia);
        }

        else if(tipoHidato.equals(TipoHidato.HEXGONAL)){
            hidatoCreado = new HidatoHexagonal(numFilas, numColumnas, tipoAdyacencia);
        }
        else if(tipoHidato.equals(TipoHidato.TRIANGULAR)){
            hidatoCreado = new HidatoTriangular(numFilas, numColumnas, tipoAdyacencia);
        }

    }

    /**
     * Constructora de un hidato aleatorio por dificultad, el atributo hidatoCreado queda inicialitzado a un hidato aleatorio
     * de la dificultad especificada.
     * @param dificultad La dificultad del hidato a crear
     */

    public void creaHidatoPorDificultad(Dificultad dificultad){
        int forma = ThreadLocalRandom.current().nextInt(0, 3);
        int adj = ThreadLocalRandom.current().nextInt(0, 2);
        int celdas;
        if(dificultad.equals(Dificultad.FACIL)) {
            celdas = ThreadLocalRandom.current().nextInt(9, 40);
        }
        else{
            celdas = ThreadLocalRandom.current().nextInt(16, 55);
        }

        if(forma == 0 && adj == 0) hidatoCreado = new HidatoCuadrado(celdas, TipoAdyacencia.LADO);
        else if(forma == 0 && adj == 1) hidatoCreado = new HidatoCuadrado(celdas, TipoAdyacencia.LADOYVERTICE);
        else if(forma == 1 && adj == 0) hidatoCreado = new HidatoTriangular(celdas, TipoAdyacencia.LADO);
        else if(forma == 1 && adj == 1) hidatoCreado = new HidatoTriangular(celdas, TipoAdyacencia.LADOYVERTICE);
        else if(forma == 2) hidatoCreado = new HidatoHexagonal(celdas, TipoAdyacencia.LADO);

        hidatoCreado.generaAleatorioPorDificultad(celdas,dificultad);

        asociaRanking();
    }

    /**
     * Crea un hidato aleatorio con las restricciones que le imponen los parametros del metodo
     * @param tipoHidato La topologia del hidato a crear
     * @param numCeldas El número de celdas totales que tendrá el hidato
     * @param numCeldasFijas El número de celdas con valor fijo del hidato
     * @param numCeldasAgujero El número de celdas bloqueadas/agujeros del hidato
     * @param tipoAdyacencia EL tipo de adyacencia del hidato
     */

    public void creaHidatoAleatorio(TipoHidato tipoHidato,int numCeldas,int numCeldasFijas,int numCeldasAgujero, TipoAdyacencia tipoAdyacencia){
        if(tipoHidato == TipoHidato.CUADRADO) {
            hidatoCreado = new HidatoCuadrado(numCeldas, tipoAdyacencia);
        }

        else if(tipoHidato == TipoHidato.HEXGONAL){
            hidatoCreado = new HidatoHexagonal(numCeldas, tipoAdyacencia);
        }

        else if(tipoHidato == TipoHidato.TRIANGULAR){
            hidatoCreado = new HidatoTriangular(numCeldas, tipoAdyacencia);
        }
        hidatoCreado.generaTableroAleatorio(numCeldas,numCeldasAgujero,numCeldasFijas);
        hidatoCreado.decideDificultad();
        asociaRanking();
    }

    public String adyacenciaHidato(){
        TipoAdyacencia adj = hidatoCreado.getAdyacencia();
        if(adj.equals(TipoAdyacencia.LADO)) return "Lado";
        else return "Lado y vertice";
    }

    public String formaHidato(){
        TipoHidato forma = hidatoCreado.getTipoHidato();
        if(forma.equals(TipoHidato.CUADRADO)) return "Cuadrado";
        else if(forma.equals(TipoHidato.TRIANGULAR)) return "Triangular";
        else return "Hexagonal";
    }

    /**
     * Devuelve el controlador de partida asociado al hidato que se ha creado con anterioridad
     * @return Un objeto del tipo JugarHidatosCTRL con una partida asociada al hidatoCreado y al jugador actual
     */

    public JugarHidatosCTRL getControladorPartida(){
        jugarHidatosCTRL.inicializa(hidatoCreado,logInCTRL.getJugador());
        return jugarHidatosCTRL;
    }

    /**
     * Crea las Celdas del Hidato interpretando los caracteres que codifican los distintos tipos de Celda.
     * No se comprueba que el <i>input</i> sea válido.
     *
     * @param celdas ArrayList de Strings, cada entrada contiene la codificación en carácteres de una fila de Celdas.
     */
    public boolean añadirCeldasHidato(ArrayList <String> celdas) {
        int files = hidatoCreado.getNumFilas();
        int columnes = hidatoCreado.getNumColumnas();

        boolean tieneUno = false;  //para comprovar si ha introducido la casilla con un 1

        for (int i = 0; i < files; i++) {
            String fila = celdas.get(i);
            String[] celda = fila.split(",");
            for (int j = 0; j < columnes; j++) {

                //cojemos el tipo de celda que contiene esa posición del string
                TipoCelda tipus = stringToCelda(celda[j]);

                //si la celda no es fija, no tendrá valor
                if(!tipus.equals(TipoCelda.FIJA)){
                    hidatoCreado.nuevaCelda(tipus,i,j);
                }
                //si es fija, tiene valor y por lo tanto tenemos que cojer el valor numerico del string
                else{
                    hidatoCreado.nuevaCelda(tipus,i,j,Integer.valueOf(celda[j]));
                    if(Integer.valueOf(celda[j]) == 1) tieneUno = true;
                }
            }
        }
        hidatoCreado.decideDificultad();
        //devolvemos si el hidato tiene solucion
        asociaRanking();
        return (tieneUno && hidatoCreado.tieneSolucion());
    }

    public void guardaHidato(){
        persistenciaCTRL.guardaHidato(hidatoCreado);
    }

    private TipoCelda stringToCelda(String s){
        if(s.equals("*")) return TipoCelda.AGUJERO;
        else if(s.equals("#")) return TipoCelda.INVISIBLE;
        else if(s.equals("?")) return TipoCelda.VARIABLE;
        else return TipoCelda.FIJA;
    }

    /**
     * Devuelve un hidato en formato String
     */
    public ArrayList<String> printHidato(){
        int files = hidatoCreado.getNumFilas();
        int columnes = hidatoCreado.getNumColumnas();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < files; i++) {
            String celes = "";
            for (int j = 0; j < columnes; j++) {
                Celda c = hidatoCreado.getCeldaTablero(i, j);
                celes = celes.concat(celdaToString(c));
                celes = celes.concat(",");
            }
            result.add(celes);
        }
        return result;
    }

    /**
     * Devuelve la representación del hidato creado
     * @return Un objeto tipo HidatoRep que contiene la representación del hidato creado
     */

    public HidatoRep getRepresentacionHidato(){
        HidatoRep rep = new HidatoRep();
        rep.setParams(0, hidatoCreado.getNumColumnas(), hidatoCreado.getNumFilas(),
                hidatoCreado.getTipoHidato(), hidatoCreado.getAdyacencia());

        for(int i=0;i<hidatoCreado.getNumFilas();i++){
            for(int j=0;j<hidatoCreado.getNumColumnas();j++){
                rep.tablero[i][j] = celdaToString(hidatoCreado.getCeldaTablero(i,j));
            }
        }
        return rep;
    }

    public HidatoRep getRepresentacionSolucion(){
        //TODO encapsular amb laltra funcio un altre dia
        HidatoRep rep = new HidatoRep();
        rep.setParams(0, hidatoCreado.getNumColumnas(), hidatoCreado.getNumFilas(),
                hidatoCreado.getTipoHidato(), hidatoCreado.getAdyacencia());

        for(int i=0;i<hidatoCreado.getNumFilas();i++){
            for(int j=0;j<hidatoCreado.getNumColumnas();j++){
                rep.tablero[i][j] = celdaToString(hidatoCreado.getCeldaTableroSolucion(i,j));
            }
        }
        return rep;
    }

    /**
     * Devuelve un array de strings que contiene la solución del hidato del controlador
     */
    public ArrayList<String> printSolucion(){

        int files = hidatoCreado.getNumFilas();
        int columnes = hidatoCreado.getNumColumnas();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < files; i++) {
            String celes = "";
            for (int j = 0; j < columnes; j++) {
                Celda c = hidatoCreado.getCeldaTableroSolucion(i, j);
                celes = celes.concat(celdaToString(c));
                celes = celes.concat(",");
            }
            result.add(celes);
        }
        return result;
    }

    private String celdaToString(Celda c){
        if(c.getTipo().equals(TipoCelda.AGUJERO)) return "*";
        else if(c.getTipo().equals(TipoCelda.INVISIBLE)) return "#";
        else if(c.getTipo().equals(TipoCelda.VARIABLE)){
            if(c.tieneValor()) return String.valueOf(c.getValor());
            else return "?";
        }
        else return String.valueOf(c.getValor());
    }

    private void asociaRanking(){
        try {
            hidatoCreado.asociaRanking(persistenciaCTRL.obtenRanking(hidatoCreado.getDificultad()));
        }
        catch (NoSuchFileException e){
            Ranking r = new Ranking();
            persistenciaCTRL.guardaRanking(r,hidatoCreado.getDificultad());
            hidatoCreado.asociaRanking(r);
        }
    }


}
