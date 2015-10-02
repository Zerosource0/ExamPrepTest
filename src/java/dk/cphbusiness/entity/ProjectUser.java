/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sofus
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ProjectUser.findAll", query = "SELECT p FROM ProjectUser p"),
    @NamedQuery(name = "ProjectUser.findById", query = "SELECT p FROM ProjectUser p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectUser.findByCreated", query = "SELECT p FROM ProjectUser p WHERE p.created = :created"),
    @NamedQuery(name = "ProjectUser.findByEmail", query = "SELECT p FROM ProjectUser p WHERE p.email = :email"),
    @NamedQuery(name = "ProjectUser.findByUsername", query = "SELECT p FROM ProjectUser p WHERE p.userName = :userName")})
@XmlRootElement
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date created;

    public ProjectUser() {
    }

    @ManyToMany(mappedBy = "projectUsers")
    private List<Project> contributors = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ProjectUser)) {
            return false;
        }
        ProjectUser other = (ProjectUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entity.ProjectUser[ id=" + id + " ]";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlTransient
    public List<Project> getContributors() {
        return contributors;
    }

    public void setContributors(List<Project> contributors) {
        this.contributors = contributors;
    }

    public void setContributor(Project contributor) {
        contributors.add(contributor);
    }

}
