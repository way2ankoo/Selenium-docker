package org.ankit.tests.vendorportal.model;

public record VendorPortalTestData(String username,
                                   String password,
                                   String monthlyEarnings,
                                   String annualEarnings,
                                   String profitMargin,
                                   String availableInventory,
                                   String searchKeyword,
                                   int searchResultsCount
                                   ) {
}
