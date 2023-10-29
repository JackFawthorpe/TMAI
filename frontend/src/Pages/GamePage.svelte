<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";
    import BoardCard from "../Components/Game/GameView/BoardCard.svelte";
    import type {Game} from "../Types/Game";
    import {SocketAPI} from "../apis/SocketAPI";
    import GlobalParametersCard from "../Components/Game/GameView/GlobalParametersCard.svelte";
    import {gameStore} from "../apis/GameStore";
    import {onDestroy, onMount} from "svelte";
    import GameNav from "../Components/Game/GameNav/GameNav.svelte";

    let game: Game = null;
    const unsubscribe = gameStore.subscribe((value) => game = value);
    onDestroy(unsubscribe);

    let connected: boolean = false;

    onMount(async () => {
        try {
            game = await API.getGame();
            gameStore.set(game);

            SocketAPI.onConnect = () => {
                connected = true;
            };
            SocketAPI.onDisconnect = () => {
                connected = false;
            };
            SocketAPI.onMessage = (frame) => {
                gameStore.set(JSON.parse(frame.body));
            };
            SocketAPI.connect();
        } catch (e) {
            if (e.response.status === 404) {
                navigate("game/create");
            } else {
                throw e;
            }
        }
    });
</script>

{#if game === null}
    <Loader/>
{:else}
    <div class="container-fluid h-screen flex gap-2 pe-2">
        <GameNav connected={connected}/>
        <div class="w-[60%] m-auto flex flex-col items-center gap-2">
            <BoardCard/>
            <GlobalParametersCard/>
        </div>
    </div>
{/if}