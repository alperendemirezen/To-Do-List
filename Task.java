public class Task {

    private String name;
    private String description;
    private boolean complete;

    public Task(String name,String description){
        this.name = name;
        this.description = description;
        complete = false;
    }
    public Task(String name){
        this.name = name;
        description = "There is no description";
        complete = false;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
