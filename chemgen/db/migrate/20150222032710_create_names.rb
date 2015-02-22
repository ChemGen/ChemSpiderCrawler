class CreateNames < ActiveRecord::Migration
  def change
    create_table :names do |t|
      t.timestamps null: false
      t.string :name
      t.boolean :primary
    end
  end
end
