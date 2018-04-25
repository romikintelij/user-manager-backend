package zab.romik.usermanagement.usecases.request;

public class Paging {
    private int page;

    /*
     * (non-javadoc)
     *
     * Getter current page
     */
    public int getPage() {
        return page;
    }

    /*
     * (non-javadoc)
     *
     * Setter for page
     */
    public void setPage(int page) {
        this.page = page;
    }
}
