package com.sanvui.model.entity;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 03/11/2021-1:16 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Entity
@Table(name = "contactstore")
public class ContactStore {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @Column(name = "facebook", columnDefinition = "varchar(255)")
    @NotNull(message = "{contact_store.facebook.null}")
    @URL(regexp = "(?:(?:http|https):\\/\\/)?(?:www.)?facebook.com\\/(?:(?:\\w)*#!\\/)?(?:pages\\/)?(?:[?\\w\\-]*\\/)?(?:profile.php\\?id=(?=\\d.*))?([\\w\\-]*)?"
            , message = "{contact_store.facebook.link}")
    private String facebook;

    @Column(name = "phone", columnDefinition = "varchar(12)")
    @NotNull(message = "{contact_store.phone.null}")
    @Pattern(regexp = "^[0]\\d{9}$", message = "{phone.valid}")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(100)")
    @Email(message = "{contact_store.email.valid}")
    @NotNull(message = "{contact_store.email.null}")
    private String email;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    @Size(min = 3, max = 255, message = "{contact_store.address.size}")
    @NotNull(message = "{contact_store.address.null}")
    private String address;

    @Column(name = "twitter", columnDefinition = "nvarchar(255)")
    @Size(min = 3, max = 255, message = "{contact_store.twitter.size}")
    @NotNull(message = "{contact_store.twitter.null}")
    @URL(message = "{contact_store.twitter.regex}")
    private String twitter;

    @Column(name = "status", columnDefinition = "bit")
    @Range(min = 0, max = 1, message = "{contact_store.status.range}")
    @NotNull(message = "{contact_store.status.null}")
    private int status;

//    constructor

    public ContactStore() {
    }

    public ContactStore(int contactId, String facebook, String phone, String email, String address, String twitter, int status) {
        this.contactId = contactId;
        this.facebook = facebook;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.twitter = twitter;
        this.status = status;
    }

    public ContactStore(String facebook, String phone, String email, String address, String twitter, int status) {
        this.facebook = facebook;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.twitter = twitter;
        this.status = status;
    }

//    getter setter

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    toString

    @Override
    public String toString() {
        return "ContactStore{" +
                "contactId=" + contactId +
                ", facebook='" + facebook + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", twitter='" + twitter + '\'' +
                ", status=" + status +
                '}';
    }
}
