import type {Player} from "./Player";
import type {Board} from "./Board";
export interface Game {
    players: Player[]
    board: Board

    globalParameters: GlobalParameters
}

export interface GlobalParameters {
    waterToPlace: number
    oxygenLevel: number
    temperature: number
}