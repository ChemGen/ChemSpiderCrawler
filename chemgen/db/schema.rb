# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20150222165715) do

  create_table "compounds", force: :cascade do |t|
    t.datetime "created_at",                                               null: false
    t.datetime "updated_at",                                               null: false
    t.text     "inchi_code",        limit: 65535
    t.string   "inchi_key",         limit: 255
    t.text     "smiles_string",     limit: 65535
    t.text     "molecular_formula", limit: 65535
    t.decimal  "mass",                            precision: 11, scale: 5
    t.decimal  "logp",                            precision: 7,  scale: 5
    t.integer  "b_rotn",            limit: 4
    t.decimal  "volume",                          precision: 9,  scale: 5
    t.integer  "lip_violation",     limit: 4
    t.decimal  "asa_p",                           precision: 9,  scale: 5
    t.boolean  "natural",           limit: 1
  end

  create_table "molfiles", force: :cascade do |t|
    t.datetime "created_at",                null: false
    t.datetime "updated_at",                null: false
    t.integer  "compound_id", limit: 4
    t.string   "name",        limit: 255
    t.text     "data",        limit: 65535
  end

  create_table "names", force: :cascade do |t|
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
    t.string   "name",        limit: 255
    t.boolean  "primary",     limit: 1
    t.integer  "compound_id", limit: 4
    t.string   "inchi_key",   limit: 255
  end

end