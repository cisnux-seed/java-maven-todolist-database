package dev.cisnux.todolist.entity;

public record Todo(int id, String activity) {
    public Todo(String activity) {
        this(-1, activity);
    }

    public static class Builder {
        private int mutableId;
        private String mutableActivity;

        public Builder setId(int id) {
            this.mutableId = id;
            return this;
        }

        public void setActivity(String mutableActivity) {
            this.mutableActivity = mutableActivity;
        }

        public Builder(Todo todo) {
            this.mutableId = todo.id;
            this.mutableActivity = todo.activity;
        }

        public Todo build() {
            return new Todo(mutableId, mutableActivity);
        }
    }

    public Builder copy() {
        return new Builder(this);
    }
}
