package zab.romik.usermanagement.usecases.request;

import javax.validation.constraints.NotEmpty;

public class GroupRequest {
    @NotEmpty
    private String name;

    /*
     * (non-javadoc)
     *
     * Геттер для имени группы
     */
    public String getName() {
        return name;
    }

    /*
     * (non-javadoc)
     *
     * Сеттер для имени группы
     */
    public void setName(String name) {
        this.name = name;
    }
}
