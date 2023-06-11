<script>
    import {onMount} from "svelte";
    import {API} from "../apis/API.js";

    let testString = "Loading";

    onMount(() => {
        API.get(false).then((data) => {
            console.log(data);
            testString = data;
        }).catch((error) => {
            console.log(error)
        });
    })
</script>

<div>
    <h1>Body</h1>
    <h3>{testString}</h3>
</div>