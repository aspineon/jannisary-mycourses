package entities.tryouts;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class TestVCListener {
	private List<SelectItem> bankalar;
    private List<SelectItem> subeler;
    private String selectedBanka = "";
    private String selectedSube;
    
    public TestVCListener() {
        bankalar = new ArrayList<SelectItem>();
        bankalar.add(new SelectItem("YKB", "Yapi Kredi Bankasi"));
        bankalar.add(new SelectItem("GRN", "Garanti Bankasi"));
        bankalar.add(new SelectItem("AKB", "Akbank"));
    }
    
    public List<SelectItem> getBankalar() {
        return bankalar;
    }
    
    public void setBankalar(List<SelectItem> bankalar) {
        this.bankalar = bankalar;
    }

    public List<SelectItem> getSubeler() {
        if (subeler == null)
            loadSube();
        return subeler;
    }

    public void setSubeler(List<SelectItem> subeler) {
        this.subeler = subeler;
    }

    public String getSelectedBanka() {
        return selectedBanka;
    }

    public void setSelectedBanka(String selectedBanka) {
        this.selectedBanka = selectedBanka;
    }

    public String getSelectedSube() {
        return selectedSube;
    }

    public void setSelectedSube(String selectedSube) {
        this.selectedSube = selectedSube;
    }

    public void handleValueChange(ValueChangeEvent event) {
        System.out.println("Component Id = " + event.getComponent().getId());
        String oldValue = (String) event.getOldValue();
        String newValue = (String) event.getNewValue();
        System.out.println("Old value = " + oldValue);
        System.out.println("New value = " + newValue);
        selectedBanka = newValue;
        loadSube();
    }

    public void save() {
        System.out.println("Banka = " + this.selectedBanka);
        System.out.println("Sube = " + this.selectedSube);
    }

    private void loadSube() {
        if (selectedBanka == null)
            selectedBanka = "";
        subeler = new ArrayList<SelectItem>();
        if (selectedBanka.equals("YKB")) {
            subeler.add(new SelectItem("YKB-AVC", "Avcilar"));
            subeler.add(new SelectItem("YKB-BES", "Besiktas"));
            subeler.add(new SelectItem("YKB-SUA", "Suadiye"));
            subeler.add(new SelectItem("YKB-KDK", "Kadikoy"));
        } else if (selectedBanka.equals("GRN")) {
            subeler.add(new SelectItem("GRN-BOS", "Bostanci"));
            subeler.add(new SelectItem("GRN-MLT", "Maltepe"));
            subeler.add(new SelectItem("GRN-KYL", "Kucukyali"));
        } else if (selectedBanka.equals("AKB")) {
            subeler.add(new SelectItem("AKB-BRK", "Bakirkoy"));
            subeler.add(new SelectItem("AKB-FLR", "Florya"));
            subeler.add(new SelectItem("AKB-KCM", "Kucukcekmece"));
            subeler.add(new SelectItem("AKB-SRN", "Sirinevler"));
            subeler.add(new SelectItem("AKB-MRT", "Merter"));
        } else {
            subeler.add(new SelectItem("", "Banka secilmedi"));
        }
    }
}
