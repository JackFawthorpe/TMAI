export interface Card {
    title: string,
    cost: number,
    tags: Array<string>
}

export interface Cell {
    type: "BUILDING" | "SPACE" | "SCIENCE" | "PLANT" | "MICROBE" | "ANIMAL" | "POWER" | "JOVIAN" | "EARTH" | "CITY" | "EVENT"
 }

export enum TagImage {
    BUILDING = "src/Resources/Building.png",
    SPACE = "src/Resources/Space.png",
    SCIENCE = "src/Resources/Science.png",
    PLANT = "src/Resources/PlantTag.png",
    MICROBE = "src/Resources/Microbe.png",
    ANIMAL = "src/Resources/Animal.png",
    POWER = "src/Resources/Power.png",
    JOVIAN = "src/Resources/Jovian.png",
    EARTH = "src/Resources/Earth.png",
    CITY = "src/Resources/City.png",
    EVENT = "src/Resources/Event.png"
}