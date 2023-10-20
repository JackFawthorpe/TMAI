export interface Board {
    cells: Array<Array<Cell>>
}

export interface Cell {
    type: "EMPTY" | "CITY" | "SPECIAL" | "OCEAN" | "GREENERY"
}

export enum CellImage {
    EMPTY = "src/Resources/img/EmptyHex.svg",
    CITY = "src/Resources/img/CityHex.svg",
    SPECIAL = "src/Resources/img/SpecialHex.svg",
    OCEAN = "src/Resources/img/OceanHex.svg",
    GREENERY = "src/Resources/img/GreeneryHex.svg"
}