package com.project2.Project2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String username;

    private String password;

    @Indexed(unique = true, sparse = true)
    private String email;

    private String firstName;
    private String lastName;

    private List<String> wishlist;
    private List<GrantedAuthority> authorities;  // Store authorities directly

    // Default constructor
    public User() {
        this.authorities = new ArrayList<>();
    }

    // Constructor with authorities
    public User(String username, String password, String email, String firstName, String lastName, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    // Constructor without authorities (defaults to an empty list)
    public User(String username, String password, String email, String firstName, String lastName) {
        this(username, password, email, firstName, lastName, new ArrayList<>());
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }

    // Set authorities directly (for registration or updates)
    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = new ArrayList<>(authorities);
    }

    // Methods required by UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

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

    // Add a book to the wishlist
    public void addToWishlist(String bookId) {
        if (this.wishlist == null) {
            this.wishlist = new ArrayList<>();
        }
        this.wishlist.add(bookId);
    }

    // Remove a book from the wishlist
    public void removeFromWishlist(String bookId) {
        if (this.wishlist != null) {
            this.wishlist.remove(bookId);
        }
    }
}

