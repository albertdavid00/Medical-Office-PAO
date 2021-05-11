package prescription;

import java.util.Objects;

public class Medicine {
    private String medName;
    private int price;
    private int quantity;
    private int prescriptionId;
    private int idMedicine;
    private static int counterMed = 0;

    public Medicine(String medName, int price, int quantity) {
        this.medName = medName;
        this.price = price;
        this.quantity = quantity;
        prescriptionId = -1;
        counterMed++;
        idMedicine = counterMed;
    }
    public int getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(int idMedicine) {
        this.idMedicine = idMedicine;
    }

    public static int getCounterMed() {
        return counterMed;
    }

    public static void setCounterMed(int counterMed) {
        Medicine.counterMed = counterMed;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getMedName() { return medName; }

    public void setMedName(String medName) { this.medName = medName; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return
                "medicine='" + medName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                "; ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return price == medicine.price && quantity == medicine.quantity && Objects.equals(medName, medicine.medName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medName, price, quantity);
    }
}
