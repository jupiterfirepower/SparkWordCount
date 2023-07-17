# Spark Word Count

*add project short description*

## Download Spark
https://spark.apache.org/downloads.html<br>
Download Spark: spark-3.4.1-bin-hadoop3-scala2.13.tgz<br>
<br>
extract <br>
tar zxvf spark-3.4.1-bin-hadoop3-scala2.13.tgz -C ~/Downloads/spark-3.4.1<br>
tar -zxvf spark-3.4.1-bin-hadoop3-scala2.13.tgz<br>
gunzip -c spark-3.4.1-bin-hadoop3-scala2.13.tgz | tar xvf -<br>
<br>
in current directory<br>
sudo mv spark-3.4.1 /usr/local/spark<br>
for example<br>
sudo mv ~/Downloads/spark-3.4.1 /usr/local/spark<br>
<br>
Add path variables.<br>
For fish:<br>
set SPARK_HOME /usr/local/spark; set -gx PATH $PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin; set -gx PATH $PATH:/usr/lib/docker/cli-plugins/<br>
<br>
./config/fish/config.fish content.<br>
source /usr/share/cachyos-fish-config/cachyos-config.fish<br>
<br>
set -q GHCUP_INSTALL_BASE_PREFIX[1]; or set GHCUP_INSTALL_BASE_PREFIX $HOME ; set -gx PATH $HOME/.cabal/bin /home/jupiter/.ghcup/bin $PATH;  # ghcup-env<br>
set DOTNET_ROOT $HOME/dotnet; set -gx PATH $PATH:$HOME/dotnet;  set SPARK_HOME /usr/local/spark; set -gx PATH $PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin;<br>
set -gx PATH $PATH:/usr/lib/docker/cli-plugins/<br>

## Accessing the library
*How to access the code*
git clone https://github.com/jupiterfirepower/SparkWordCount.git
in root application directory run<br>
sbt assembly<br>

## Start Spark Master.
start-master.sh
## Start Spark Worker Node.
start-worker.sh spark://localhost:7077 -c 2 -m 1G<br>

## Run Spark App.
<p>
spark-submit \\<br>
        --class org.apache.streaming.wordcount.WordCountApp \\<br>
        --master spark://localhost:7077 \\<br>
        --executor-memory 1G \\<br>
        --total-executor-cores 1 \\<br>
        /home/jupiter/WORK/PROJECTS/sparkwordcount/target/scala-2.13/sparkwordcount-assembly-0.0.1.jar
</p>



