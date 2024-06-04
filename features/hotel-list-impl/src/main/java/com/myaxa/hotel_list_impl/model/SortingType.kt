package com.myaxa.hotel_list_impl.model

internal enum class SortingType(val text: String) {
    NONE("none"),
    BY_DISTANCE("By distance to the center"),
    BY_FREE_ROOMS_NUMBER("By free rooms number"),
}