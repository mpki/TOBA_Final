/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.db;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matt
 */
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findById", query = "SELECT t FROM Transaction t WHERE t.id = :id"),
    @NamedQuery(name = "Transaction.findByUsername", query = "SELECT t FROM Transaction t WHERE t.username = :username"),
    @NamedQuery(name = "Transaction.findByTransfer", query = "SELECT t FROM Transaction t WHERE t.transfer = :transfer"),
    @NamedQuery(name = "Transaction.findByDestination", query = "SELECT t FROM Transaction t WHERE t.destination = :destination"),
    @NamedQuery(name = "Transaction.findByID", query = "SELECT t FROM Transaction t WHERE t.id = :id"),
    @NamedQuery(name = "Transaction.findByDate", query = "SELECT t FROM Transaction t WHERE t.date = :date")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Username")
    private String username;
    @Column(name = "Source")
    private String source;
    @Column(name = "Transfer")
    private Float transfer;
    @Size(max = 100)
    @Column(name = "Destination")
    private String destination;
    @Size(max = 200)
    @Column(name = "Date")
    private String date;

    public Transaction() {
           Date d = new Date();
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   this.date = dateFormat.format(d);
	   Calendar cal = Calendar.getInstance();
           this.date += " "+dateFormat.format(cal.getTime());

        
    }
    
    public Transaction(String Username, String Source, String Destination, Float Transfer)
    {
        this.username = Username;
        this.source = Source;
        this.destination = Destination;
        this.transfer = Transfer;
        updateTime();
    }

    public String updateTime()
    {
           Date d = new Date();
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   Calendar cal = Calendar.getInstance();
           this.date = dateFormat.format(cal.getTime());

           return date;
    }
    
    public Transaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public Float getTransfer() {
        return transfer;
    }

    public void setTransfer(Float transfer) {
        this.transfer = transfer;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "toba.beans.Transactions[ id=" + id + " ]";
    }
    
}
