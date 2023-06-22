export interface Board {
    cells: Array<Array<Cell>>
}

export interface Cell {
    type: "EMPTY" | "CITY" | "SPECIAL" | "OCEAN" | "GREENERY"
}

export enum CellImage {
    EMPTY = "src/Resources/EmptyHex.svg",
    CITY = "src/Resources/CityHex.svg",
    SPECIAL = "src/Resources/SpecialHex.svg",
    OCEAN = "src/Resources/OceanHex.svg",
    GREENERY = "src/Resources/GreeneryHex.svg"
}