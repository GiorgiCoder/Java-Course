public class Halt extends Instruction{
    public Halt(){
    }
    public void execute(Simulator simulator){
        simulator.setHalted(true);
    }
}