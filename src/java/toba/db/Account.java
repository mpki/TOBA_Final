/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import toba.beans.User;

/**
 *
 * @author Matt
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findBySavings", query = "SELECT a FROM Account a WHERE a.savings = :savings"),
    @NamedQuery(name = "Account.findByChecking", query = "SELECT a FROM Account a WHERE a.checking = :checking"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "Savings")
    private int savings;
    @Size(max = 100)
    @Column(name = "Checking")
    private int checking;
    @Size(max = 150)
    @Column(name = "Username")
    private String username;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Balance")
    private float balance;
    @ManyToOne
    private User user;
    
    @OneToMany
    private Collection<Transaction> transactions;
    
    public AccountType Type;
    
    public enum AccountType
    {
        
        
        SAVINGS(0), CHECKING(1);
        
        private int _AccountType = 0;
        
        AccountType(int Actype)
        {
            this._AccountType = Actype;
        }
        
    }
    
    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(User user, float Balance)
    {
        this.user = user;
        this.balance = Balance;
    }
    
    public void setAccount(AccountType t)
    {
        this.Type = t;
    }
   
    public void Credit(float transfer)
    {
        this.balance += transfer;
    }
    
    public void Debit(float transfer)
    {
        this.balance -= transfer;
    }
    
    public void AddTransaction(Transaction t)
    {
        transactions.add(t);
    }
    
    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    public int getChecking() {
        return checking;
    }

    public void setChecking(int checking) {
        this.checking = checking;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "toba.db.Account[ id=" + id + " ]";
    }
    
}
