import type {Card} from "./Card";

export interface Player {
    name: string
    inventory: Inventory
    human: boolean
    id: number
    playerDeck: PlayerDeck
}

export interface PlayerDeck {
    hand: Card[]
    played: Card[]
}