<script lang="ts">

    import Loader from "../Components/Layout/Loader.svelte";
    import {API} from "../apis/API";
    import {navigate} from "svelte-routing";
    import BoardCard from "../Components/Game/GameView/BoardCard.svelte";
    import type {Game} from "../Types/Game";
    import {SocketAPI} from "../apis/SocketAPI";
    import GlobalParametersCard from "../Components/Game/GameView/GlobalParametersCard.svelte";
    import {contexts} from "../apis/Contexts";
    import {onDestroy} from "svelte";
    import GameNav from "../Components/Game/GameNav/GameNav.svelte";

    let game: Game = null;
    const unsubscribe = contexts.subscribe((value) => game = value);
    onDestroy(unsubscribe);

    let connected: boolean;
    const loadGame = async () => {
        try {
            game = await API.getGame();
            contexts.set(game);
            SocketAPI.onConnect = () => {
                connected = true
            };
            SocketAPI.onDisconnect = () => {
                connected = false
            };
            SocketAPI.onMessage = (frame) => {
                console.log('resetting store');
                contexts.set(JSON.parse(frame.body));
            }
            SocketAPI.connect();

        } catch (e) {
            if (e.response.status === 404) {
                navigate("game/create")
            } else {
                throw e
            }
        }
    }

    const promise = loadGame();

</script>

{#await promise}
    <Loader/>
{:then _}
    <div class="container-fluid h-screen flex gap-2 pe-2">
        <GameNav connected={connected}/>
        <div class="w-[60%] m-auto flex flex-col items-center gap-2">
            <BoardCard/>
            <GlobalParametersCard/>
        </div>
    </div>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}