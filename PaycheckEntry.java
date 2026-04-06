import java.time.LocalDate;

public class PaycheckEntry
{ 
    private String id;
    private double amount;
    private LocalDate dateObtained;
    
    public PaycheckEntry(String id, double amount, LocalDate dateObtained)
    { 
        this.id = id;
        this.amount = amount;
        this.dateObtained = dateObtained;
    }
    
    public String getId()
    {
        return id;
    }
    
    public double getAmount()
    {
        return amount;
    }
    
    public LocalDate getDateObtained()
    {
        return dateObtained;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    
    public void setDateObtained(LocalDate dateObtained)
    {
        this.amount = amount;
    }
}