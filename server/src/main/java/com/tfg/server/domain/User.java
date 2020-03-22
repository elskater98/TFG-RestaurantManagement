package com.tfg.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "RestaurantManagementAPI") //Avoid collision with system table User in Postgres
@Data
//@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements UserDetails {

    //Constructor
    public User() {
    }

    public User(String username, @NotBlank @Email String email,
                @NotBlank @Length(min = 4,max = 256) String password, @NotNull Role role) {
        this.username=username;
        this.email=email;
        this.password=password;
        encodePassword();
        this.role=role;
    }


    //Username
    @Id
    private String username;

    @Override
    public String getUsername() {
        return username;
    }

    //Email
    @NotBlank(message = "Email must not be blank.")
    @Email
    @Column(name="email",unique = true)
    private String email;


    //Password
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be blank.")
    @Length(min = 4, max = 256,message = "Password length is between 4 and 256 characters.")
    private String password;

    @JsonIgnore
    @Transient
    private boolean passwordReset;

    @Override
    public String getPassword() {
        return this.password;
    }

    public void encodePassword() {
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean isPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(boolean passwordReset) {
        this.passwordReset = passwordReset;
    }


    //Roles & Authentication
    public enum Role{
        Admin,
        Cambrer,
        Cuiner,
        Bartender,
        Propietari,
    }

    @NotNull(message = "Role must not be null.")
    public Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.role){
            case Admin:
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            case Cuiner:
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CUINER");
            case Cambrer:
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CAMBRER");
            case Bartender:
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_BARTENDER");
            case Propietari:
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_PROPIETARI");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
    }


    //Account status

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
