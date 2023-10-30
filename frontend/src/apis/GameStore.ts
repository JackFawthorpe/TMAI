import type {Writable} from "svelte/store";
import {writable} from "svelte/store";
import type {Game} from "../Types/Game";
import {onDestroy} from "svelte";
import type {Player} from "../Types/Player";

export const gameStore: Writable<Game> = writable(null);
export const playerStore: Writable<Player> = writable(null);

export function subscribeToStore(store, callback) {
    let unsubscribe = null;

    unsubscribe = store.subscribe((value) => {
        callback(value);
    });

    onDestroy(() => {
        unsubscribe();
    });
}

export function resetStores() {
    gameStore.set(null);
    playerStore.set(null);
}