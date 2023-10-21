import type {Writable} from "svelte/store";
import {writable} from "svelte/store";
import type {Game} from "../Types/Game";
import {onDestroy} from "svelte";

export const gameStore: Writable<Game> = writable(null);

export function subscribeToStore(store, callback) {
    let unsubscribe = null;

    unsubscribe = store.subscribe((value) => {
        callback(value);
    });

    onDestroy(() => {
        unsubscribe();
    });
}
