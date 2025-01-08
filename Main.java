import java.util.ArrayList;
import java.util.List;

abstract class Electrodomestico {
    private String marca;
    private String modelo;
    private int numeroProgramas;

    public Electrodomestico(String marca, String modelo, int numeroProgramas) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroProgramas = numeroProgramas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumeroProgramas() {
        return numeroProgramas;
    }

    public void setNumeroProgramas(int numeroProgramas) {
        this.numeroProgramas = numeroProgramas;
    }

    public abstract void imprimir();
}

class Lavadora extends Electrodomestico {
    private int capacidadCarga;
    private int revolucionesMinuto;

    public Lavadora(String marca, String modelo, int numeroProgramas, int capacidadCarga, int revolucionesMinuto) {
        super(marca, modelo, numeroProgramas);
        this.capacidadCarga = capacidadCarga;
        this.revolucionesMinuto = revolucionesMinuto;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public int getRevolucionesMinuto() {
        return revolucionesMinuto;
    }

    public void setRevolucionesMinuto(int revolucionesMinuto) {
        this.revolucionesMinuto = revolucionesMinuto;
    }

    @Override
    public void imprimir() {
        System.out.println("Lavadora - Marca: " + getMarca() + ", Modelo: " + getModelo() +
                ", Programas: " + getNumeroProgramas() +
                ", Capacidad de carga: " + capacidadCarga +
                ", RPM: " + revolucionesMinuto);
    }
}
class Lavavajillas extends Electrodomestico {
    private int numeroServicios;

    public Lavavajillas(String marca, String modelo, int numeroProgramas, int numeroServicios) {
        super(marca, modelo, numeroProgramas);
        this.numeroServicios = numeroServicios;
    }

    public int getNumeroServicios() {
        return numeroServicios;
    }

    public void setNumeroServicios(int numeroServicios) {
        this.numeroServicios = numeroServicios;
    }

    @Override
    public void imprimir() {
        System.out.println("Lavavajillas - Marca: " + getMarca() + ", Modelo: " + getModelo() +
                ", Programas: " + getNumeroProgramas() +
                ", Servicios: " + numeroServicios);
    }
}

class TiendaElectrodomesticos {
    private List<Electrodomestico> electrodomesticos = new ArrayList<>();

    public void anadirLavadora(Lavadora lavadora) {
        electrodomesticos.add(lavadora);
    }

    public void anadirLavavajillas(Lavavajillas lavavajillas) {
        electrodomesticos.add(lavavajillas);
    }

    public void buscarElectrodomestico(String marca, String modelo, Integer programasDesde, Integer programasHasta) {
        for (Electrodomestico e : electrodomesticos) {
            boolean coincide = (marca == null || marca.isEmpty() || e.getMarca().equalsIgnoreCase(marca)) &&
                    (modelo == null || modelo.isEmpty() || e.getModelo().equalsIgnoreCase(modelo)) &&
                    (programasDesde == null || e.getNumeroProgramas() >= programasDesde) &&
                    (programasHasta == null || e.getNumeroProgramas() <= programasHasta);

            if (coincide) {
                e.imprimir();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TiendaElectrodomesticos tienda = new TiendaElectrodomesticos();

        Lavadora l1 = new Lavadora("Balay", "BL204", 5, 7, 1200);
        Lavadora l2 = new Lavadora("AEG", "AL204", 9, 8, 1600);
        Lavadora l3 = new Lavadora("Miele", "ML204", 5, 6, 1500);

        tienda.anadirLavadora(l1);
        tienda.anadirLavadora(l2);
        tienda.anadirLavadora(l3);

        Lavavajillas lv1 = new Lavavajillas("Balay", "BV408", 8, 16);
        Lavavajillas lv2 = new Lavavajillas("Samsung", "SV408", 10, 13);

        tienda.anadirLavavajillas(lv1);
        tienda.anadirLavavajillas(lv2);

        System.out.println("Búsqueda 1:");
        tienda.buscarElectrodomestico("Balay", "", null, 7);

        System.out.println("Búsqueda 2:");
        tienda.buscarElectrodomestico("Balay", "", null, null);

        System.out.println("Búsqueda 3:");
        tienda.buscarElectrodomestico("", "", 7, 9);
    }
}
