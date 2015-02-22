class CreateMolfiles < ActiveRecord::Migration
  def change
    create_table :molfiles do |t|
      t.timestamps null: false
      t.integer :compound_id
      t.string :name
    end
  end
end
