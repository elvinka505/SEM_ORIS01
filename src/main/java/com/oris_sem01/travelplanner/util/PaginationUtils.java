package com.oris_sem01.travelplanner.util;

public class PaginationUtils {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalItems;

    public PaginationUtils(int pageNumber, int pageSize, long totalItems) {
        this.pageNumber = pageNumber < 1 ? 1 : pageNumber;
        this.pageSize = pageSize < 1 ? 10 : pageSize;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public boolean hasNextPage() {
        return pageNumber < totalPages;
    }

    public boolean hasPreviousPage() {
        return pageNumber > 1;
    }

    public int getNextPage() {
        return hasNextPage() ? pageNumber + 1 : pageNumber;
    }

    public int getPreviousPage() {
        return hasPreviousPage() ? pageNumber - 1 : pageNumber;
    }
}
