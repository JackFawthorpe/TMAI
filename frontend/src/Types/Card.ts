export interface Card {
    title: string,
    cost: number,
    tags: Array<string>
}

export interface Cell {
    type: "BUILDING" | "SPACE" | "SCIENCE" | "PLANT" | "MICROBE" | "ANIMAL" | "POWER" | "JOVIAN" | "EARTH" | "CITY" | "EVENT"
 }

export enum TagImage {
    BUILDING = "src/Resources/img/Building.png",
    SPACE = "src/Resources/img/Space.png",
    SCIENCE = "src/Resources/img/Science.png",
    PLANT = "src/Resources/img/PlantTag.png",
    MICROBE = "src/Resources/img/Microbe.png",
    ANIMAL = "src/Resources/img/Animal.png",
    POWER = "src/Resources/img/Power.png",
    JOVIAN = "src/Resources/img/Jovian.png",
    EARTH = "src/Resources/img/Earth.png",
    CITY = "src/Resources/img/City.png",
    EVENT = "src/Resources/img/Event.png"
}