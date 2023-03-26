package com.naturalgoods.backend.productType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Unit {
    KG("KG", "КГ", "КГ"),
    L("L", "Л", "Л");

    private final String nameEn;
    private final String nameKz;
    private final String nameRu;

    public String getNameEn() {
        return nameEn;
    }
    public String getNameKz() {
        return nameKz;
    }
    public String getNameRu() {
        return nameRu;
    }

}
