package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private UUID id;
    private String username;
    private String password;

    public User() {
    }

    private User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private UUID id;
        private String username;
        private String password;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(id, username, password);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "\n\tid=" + id +
                ",\n\tusername='" + username + '\'' +
                ",\n\tpassword='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;

        if (!getId().equals(user.getId())) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        return getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
