<script lang="ts">

    import type {Game} from "../../Types/Game";
    import PlayerCard from "./PlayerCard.svelte";
    import type {Player} from "../../Types/Player";
    import {contexts, subscribeToStore} from "../../apis/Contexts";

    let game: Game = null;
    subscribeToStore(contexts, (value) => (game = value));
    let players: Player[];
    $: players = game.players;
</script>

<div class="grid grid-cols-1 xl:grid-cols-2">
    {#each players as player}
        <PlayerCard player={player}/>
    {/each}
</div>