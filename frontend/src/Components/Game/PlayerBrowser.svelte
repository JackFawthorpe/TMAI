<script lang="ts">

    import type {Game} from "../../Types/Game";
    import PlayerCard from "./PlayerCard.svelte";
    import type {Player} from "../../Types/Player";
    import {gameStore, subscribeToStore} from "../../apis/GameStore";

    let game: Game = null;
    subscribeToStore(gameStore, (value) => (game = value));
    let players: Player[];
    $: players = game.players;

    let currentPlayerContext = {
        playerId: -1,
        setPlayerId: (id: number) => {
            currentPlayerContext.playerId = id;
        }
    }

</script>

<div class="grid grid-cols-1 xl:grid-cols-2">
    {#each players as player}
        <PlayerCard player={player} currentPlayerContext={currentPlayerContext}/>
    {/each}
</div>